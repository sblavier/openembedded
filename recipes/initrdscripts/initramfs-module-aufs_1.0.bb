SRC_URI = "file://98-aufs.sh"
PR = "r1"
DESCRIPTION = "An initramfs module for mount aufs."
RDEPENDS = "initramfs-uniboot aufs"

do_install() {
    install -d ${D}/initrd.d
    install -m 0755 ${WORKDIR}/98-aufs.sh ${D}/initrd.d/
}

PACKAGE_ARCH = "all"
FILES_${PN} += " /initrd.d/* "
