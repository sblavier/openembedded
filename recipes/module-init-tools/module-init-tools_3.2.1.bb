require module-init-tools.inc

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/utils/kernel/module-init-tools/module-init-tools-${PV}.tar.bz2 \
	   file://ignore_arch_directory;patch=1 \
	   file://modutils_extension;patch=1 \
	   file://no_man_rebuild;patch=1 \
	   file://manpagesopt;patch=1 "

bindir = "/bin"
sbindir = "/sbin"

do_install() {
	autotools_do_install
	for f in bin/lsmod sbin/insmod sbin/rmmod sbin/modprobe sbin/modinfo sbin/depmod; do
		mv ${D}/$f ${D}/$f.26
	done
}

pkg_postinst_module-init-tools() {
#!/bin/sh
for f in sbin/insmod sbin/modprobe sbin/rmmod sbin/depmod sbin/modinfo; do
bn=`basename $f`
   update-alternatives --install /$f $bn /$f.26 60
done
update-alternatives --install /bin/lsmod bin-lsmod /bin/lsmod.26 60
update-alternatives --install /sbin/lsmod lsmod /bin/lsmod.26 60
}

pkg_prerm_module-init-tools() {
#!/bin/sh
for f in sbin/insmod sbin/modprobe sbin/rmmod sbin/depmod sbin/modinfo; do
bn=`basename $f`
   update-alternatives --remove $bn /$f.26
done
update-alternatives --remove bin-lsmod /bin/lsmod.26
update-alternatives --remove lsmod /bin/lsmod.26
}

pkg_postinst_module-init-tools-depmod() {
#!/bin/sh
update-alternatives --install /sbin/depmod depmod /sbin/depmod.26 60
}

pkg_prerm_module-init-tools() {
#!/bin/sh
update-alternatives --remove depmod /sbin/depmod.26
}
