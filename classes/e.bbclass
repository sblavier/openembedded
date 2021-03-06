HOMEPAGE = "http://www.enlightenment.org"
SECTION = "e/apps"
SRCNAME ?= "${PN}"

SRC_URI = "svn://svn.enlightenment.org/svn/e/trunk;module=${SRCNAME};proto=http"
S = "${WORKDIR}/${SRCNAME}"

ARM_INSTRUCTION_SET = "arm"

inherit autotools pkgconfig binconfig

do_prepsources () {
  make clean distclean || true
}
addtask prepsources after do_patch before do_configure

do_configure_append() {
        find ${S} -name Makefile | xargs sed -i s:'-I$(includedir)':'-I.':g
}

export CURL_CONFIG = "${STAGING_BINDIR_CROSS}/curl-config"
export FREETYPE_CONFIG = "${STAGING_BINDIR_CROSS}/freetype-config"

# This construction is stupid, someone with more E knowledge should change it to =+ or something
# And it's in efl.bbclass as well....
PACKAGES = "${PN}-dbg ${PN}-themes ${PN} ${PN}-dev ${PN}-doc ${PN}-lib ${PN}-static"
FILES_${PN}-lib = "${libdir}/lib*.so.*"
FILES_${PN}-themes = "${datadir}/${PN}/themes ${datadir}/${PN}/data ${datadir}/${PN}/fonts ${datadir}/${PN}/pointers ${datadir}/${PN}/images ${datadir}/${PN}/users ${datadir}/${PN}/images ${datadir}/${PN}/styles"
FILES_${PN}-dev += "${includedir} ${libdir}/lib*.so"
