DESCRIPTION = "A sophisticated console ftp client"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "ClarifiedArtistic"

SRC_URI = "ftp://ftp.ncftp.com/ncftp/older_versions/ncftp-${PV}-src.tar.bz2 \
	   file://acinclude.m4 \
	   file://make.patch;patch=1 \
	   file://fixes.patch;patch=1"

inherit autotools

do_configure_prepend () {
	install -m 0644 ${WORKDIR}/acinclude.m4 acinclude.m4
}

INHIBIT_AUTO_STAGE = "1"

do_install () {
	install -d ${D}${bindir} ${D}${sysconfdir} ${D}${mandir}
	oe_runmake 'prefix=${D}${prefix}' 'BINDIR=${D}${bindir}' \
		   'SYSCONFDIR=${D}${sysconfdir}' 'mandir=${D}${mandir}' \
		   install
}
