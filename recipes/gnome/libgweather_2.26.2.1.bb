LICENSE = "LGPL"
DEPENDS = "libgnome libsoup-2.4 gtk+"

PR = "r1"

inherit gnome

EXTRA_OEMAKE = "ORBIT_IDL=${STAGING_BINDIR_NATIVE}/orbit-idl-2"

do_configure_prepend() {
        sed -i -e 's:help::'    ${S}/Makefile.am
	sed -i -e 's: doc : :g' ${S}/Makefile.am
}

do_stage() {
        autotools_stage_all
}

FILES_${PN} += "${datadir}/gnome* \
                ${datadir}/icons"

PACKAGES =+ "${PN}-locationdata"
FILES_${PN}-locationdata = "${datadir}/libgweather/Locations*"

PACKAGES =+ "libpanel-applet"
FILES_libpanel-applet = "${libdir}/libpanel-applet-2.so.*"


