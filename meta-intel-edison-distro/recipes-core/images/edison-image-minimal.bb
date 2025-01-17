DESCRIPTION = "A minimal functional image to run EDISON"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
LICENSE = "MIT"
IMAGE_INSTALL = "packagegroup-core-boot  ${CORE_IMAGE_EXTRA_INSTALL}"
IMAGE_INSTALL:append = " openssh-sftp-server"

IMAGE_LINGUAS = " "

# We don't want to include initrd - we have initramfs instead
#INITRD_LIVE = ""
DEPENDS += " core-image-minimal-initramfs"

# Do not use legacy nor EFI BIOS
PCBIOS = "0"
# Do not support bootable USB stick
NOISO = "1"
# Generate a HDD image
NOHDD = "0"
ROOTFS = ""

# Specify rootfs image type
IMAGE_FSTYPES += "btrfs ext4 live"

IMAGE_CLASSES += "qemuboot"

inherit core-image

# image-live/live-vm-common do not allow to use initramfs kernel
# and we really want to have one in the generated hddimg file.
# Otherwise it doesn't boot due to no mmc/sdhc modules being built-in in our setup).
# There seems to be no standard mechanism for doing that, but the below works.
#KERNEL_IMAGETYPE_pn-edison-image-minimal = "bzImage-initramfs-edison.bin"

IMAGE_ROOTFS_SIZE = "262144"

IMAGE_FEATURES += "package-management ssh-server-openssh"
# Allow passwordless root login and postinst logging
IMAGE_FEATURES += "debug-tweaks"

IMAGE_INSTALL:append = " wpa-supplicant"
IMAGE_INSTALL:append = " iw"
IMAGE_INSTALL:append = " bridge-utils"
IMAGE_INSTALL:append = " bluez5"
IMAGE_INSTALL:append = " kernel-modules"
IMAGE_INSTALL:append = " iptables"
IMAGE_INSTALL:append = " libstdc++"
IMAGE_INSTALL:append = " libubootenv-bin"
IMAGE_INSTALL:append = " file"
IMAGE_INSTALL:append = " pciutils"
IMAGE_INSTALL:append = " usbutils"
IMAGE_INSTALL:append = " lshw"
IMAGE_INSTALL:append = " ldd"
IMAGE_INSTALL:append = " i2c-tools"
IMAGE_INSTALL:append = " watchdog-sample"
IMAGE_INSTALL:append = " wget"
IMAGE_INSTALL:append = " sudo"

IMAGE_INSTALL:append = " screen"

# Python and some basic modules
#IMAGE_INSTALL_append = " python3"

# To allow booting alternate kernel
IMAGE_INSTALL:append = " bootsuccess"


# Wifi firmware
# removed modules, already built into kernel
IMAGE_INSTALL:append = " bcm43340-fw"
IMAGE_INSTALL:append = " bcm43340-addr"

# Gadget setup
IMAGE_INSTALL:append = " gadget"

# Adds various other tools
IMAGE_INSTALL:append = " lsof"
IMAGE_INSTALL:append = " less"

# Those are necessary to manually create partitions and file systems on the eMMC
IMAGE_INSTALL:append = " parted"
IMAGE_INSTALL:append = " e2fsprogs-e2fsck e2fsprogs-mke2fs e2fsprogs-tune2fs e2fsprogs-badblocks e2fsprogs-resize2fs libcomerr libss libe2p libext2fs dosfstools"
IMAGE_INSTALL:append = " btrfs-tools"
IMAGE_INSTALL:append = " p7zip"

# Time related
IMAGE_INSTALL:append = " tzdata"

# linux 5.0.0 requires a source of entropy, use TRNG
IMAGE_INSTALL:append = " rng-tools"

IMAGE_INSTALL:append = " libgpiod-tools"

# Add monitoring tools
IMAGE_INSTALL:append = " htop"

DEPENDS += "u-boot"

ROOTFS_POSTPROCESS_COMMAND += "install_initrd; "

install_initrd() {
    bbnote "Adding initrd to image ${IMAGE_ROOTFS}"
    install -d ${IMAGE_ROOTFS}/boot
    bbnote "from ${DEPLOY_DIR_IMAGE}/core-image-minimal-initramfs-edison.cpio.gz"
    install -m 0755 ${DEPLOY_DIR_IMAGE}/core-image-minimal-initramfs-edison.cpio.gz ${IMAGE_ROOTFS}/boot/initrd
}
