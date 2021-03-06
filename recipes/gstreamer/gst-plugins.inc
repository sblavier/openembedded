require gst-common.inc
DESCRIPTION = "Plugins for GStreamer"
DEPENDS = "gstreamer libmikmod liboil libogg tremor libvorbis"
DEPENDS += "${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'libmad libid3tag', d)}"
DEPENDS += "${@base_conditional('DISTRO', 'opendreambox', 'libmad libsoup-2.4 flac libmatroska libcdio libid3tag', '', d)}"

INC_PR = "r0"

PACKAGES_DYNAMIC = "gst-plugin-*"

SRC_URI = "http://gstreamer.freedesktop.org/src/${PN}/${PN}-${PV}.tar.bz2"

EXTRA_OECONF = "--enable-introspection=no --disable-aalib --disable-shout2 --disable-sdl --disable-cdaudio --x-includes=${STAGING_INCDIR} --x-libraries=${STAGING_LIBDIR} "
EXTRA_OECONF_append_shr = " --disable-dvdnav "
EXTRA_OECONF_opendreambox = "--disable-x --disable-aalib --disable-esd --disable-shout2 --disable-sdl --with-check=no --enable-neon "

acpaths = "-I ${S}/common/m4 -I ${S}/m4"

OE_LT_RPATH_ALLOW = "any"
OE_LT_RPATH_ALLOW[export]="1"

LIBV = "0.10"

python populate_packages_prepend () {
	gst_libdir = bb.data.expand('${libdir}/gstreamer-${LIBV}', d)
	postinst = bb.data.getVar('plugin_postinst', d, 1)
	glibdir = bb.data.expand('${libdir}', d)

	do_split_packages(d, glibdir, '^lib(.*)\.so\.*', 'lib%s', 'gstreamer %s library', extra_depends='', allow_links=True)
	do_split_packages(d, gst_libdir, '^libgst(.*)\.so$', 'gst-plugin-%s', 'GStreamer plugin for %s', postinst=postinst, extra_depends='')
	do_split_packages(d, gst_libdir, '^libgst(.*)\.l?a$', 'gst-plugin-%s-dev', 'GStreamer plugin for %s (development files)', extra_depends='')
	
	pn = bb.data.getVar('PN', d, 1)
	metapkg =  pn + '-meta'
	bb.data.setVar('ALLOW_EMPTY_' + metapkg, "1", d)
	bb.data.setVar('FILES_' + metapkg, "", d)
	blacklist = [ pn + '-locale', pn + '-dev', pn + '-dbg', pn + '-doc' ]
	metapkg_rdepends = []
	packages = bb.data.getVar('PACKAGES', d, 1).split()
	for pkg in packages[1:]:
		if not pkg in blacklist and not pkg in metapkg_rdepends and not pkg.endswith('-dev') and not pkg.count('locale') and not pkg.count('-static'):
			metapkg_rdepends.append(pkg)
	bb.data.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends), d)
	bb.data.setVar('DESCRIPTION_' + metapkg, pn + ' meta package', d)
	packages.append(metapkg)
	bb.data.setVar('PACKAGES', ' '.join(packages), d)

}

ALLOW_EMPTY = "1"

PACKAGES += "${PN}-apps"
FILES_${PN}-apps = "${bindir}"

FILES_${PN} = "${datadir}/gstreamer-0.10"
FILES_${PN}-dbg += "${libdir}/gstreamer-${LIBV}/.debug"


