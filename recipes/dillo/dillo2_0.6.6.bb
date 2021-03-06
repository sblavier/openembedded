SECTION = "x11/network"
DESCRIPTION = "Lightweight gtk+2 browser."
LICENSE = "GPL"
DEPENDS = "gtk+"
RDEPENDS = "gdk-pixbuf-loader-xpm"
SRC_URI="http://www.dillo.org/download/dillo-${PV}.tar.gz \
         file://dillo2-gcc4.patch;patch=1;pnum=1 \
         file://gtk2.patch;patch=1;pnum=1 \
         file://fix_about_syntax.patch;patch=1;pnum=1 \
         file://dillo.desktop \
         file://dillo.png \
         file://dillorc"

PRIORITY = "optional"

S = "${WORKDIR}/dillo-${PV}/"

inherit autotools pkgconfig

do_install() {
	install -d ${D}${bindir}
	install -d ${D}${datadir}/applications
	install -d ${D}${datadir}/pixmaps
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/dillo.desktop ${D}${datadir}/applications/dillo.desktop
	install -m 0644 ${WORKDIR}/dillo.png ${D}${datadir}/pixmaps/dillo.png
	install -m 0755 ${S}/src/dillo ${D}${bindir}/dillo
	install -m 0644 ${WORKDIR}/dillorc ${D}${sysconfdir}/dillorc
}
