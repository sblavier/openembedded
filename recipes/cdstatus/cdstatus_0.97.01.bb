# cdstatus OE build file

PR ="r0"
LICENSE="GPL"
HOMEPAGE = "http://cdstatus.sourceforge.net/"
FILES_${PN} += "${datadir}/cdstatus.cfg"
# need __USE_MISC to get h_addr macro in netdb.h
CFLAGS += -D__USE_MISC

SRC_URI="${SOURCEFORGE_MIRROR}/cdstatus/cdstatus-0.97.01.tar.gz \
	 file://Makefile.am.patch;patch=1 \
	 file://cddb_connect_to_server.c.patch;patch=1 \
	 file://cdstatus.patch;patch=1"

S="${WORKDIR}/cdstatus-0.97.01"

inherit autotools

do_install() {
	install -d 0755 ${D}/${bindir}
	install -d 0755 ${D}/${datadir}
	install -d 0755 ${D}/${mandir}
	install -m 0755 src/cdstatus          ${D}/${bindir}
	install -m 0644 cdstatus.cfg          ${D}/${datadir}
	install -m 0644 cdstatus.1            ${D}/${mandir}

}
