KBRANCH ?= "linux-5.15.y"

require recipes-kernel/linux/linux-yocto.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=https;branch=linux-5.15.y"

# based on andy-shev's edison kernel configs https://github.com/andy-shev/linux/commits/eds-acpi
SRC_URI:append = " file://0001-enable-to-build-a-netboot-image.cfg"
SRC_URI:append = " file://0002-enable-x2APIC.cfg"
SRC_URI:append = " file://0003-enable-NETCONSOLE_DYNAMIC.cfg"
SRC_URI:append = " file://0004-enable-STMMAC.cfg"
SRC_URI:append = " file://0005-enable-IGB.cfg"
SRC_URI:append = " file://0006-enable-IXGBE.cfg"
SRC_URI:append = " file://0007-enable-USB_RTL8152.cfg"
SRC_URI:append = " file://0008-disable-HPET.cfg"
SRC_URI:append = " file://0009-disable-i915-DRM.cfg"
SRC_URI:append = " file://0010-enable-X86_INTEL_MID.cfg"
SRC_URI:append = " file://0011-enable-INPUT_SOC_BUTTON_ARRAY.cfg"
SRC_URI:append = " file://0012-enable-I2C_HID-and-HID_MULTITOUCH.cfg"
SRC_URI:append = " file://0013-DEBUG_SHIRQ-DEBUG_LOCKDEP.cfg"
SRC_URI:append = " file://0014-enable-ACPI_DEBUG-and-ACPI_PROCFS_POWER.cfg"
SRC_URI:append = " file://0015-enable-ACPI_TABLE_UPGRADE.cfg"
SRC_URI:append = " file://0016-enable-X86_INTEL_LPSS-and-LPSS-drivers.cfg"
SRC_URI:append = " file://0017-enable-MFD_INTEL_LPSS-drivers.cfg"
SRC_URI:append = " file://0018-enable-INTEL_IDMA64-iDMA-64-bit.cfg"
SRC_URI:append = " file://0020-enable-SPI_DW.cfg"
SRC_URI:append = " file://0021-disable-HDA-audio.cfg"
SRC_URI:append = " file://0022-enable-Intel-Quark-devices.cfg"
SRC_URI:append = " file://0023-enable-DEBUG_GPIO.cfg"
SRC_URI:append = " file://0024-enable-GPIO_DWAPB.cfg"
SRC_URI:append = " file://0025-enable-GPIO_PCA953X.cfg"
SRC_URI:append = " file://0029-enable-INTEL_IDLE.cfg"
SRC_URI:append = " file://0030-enable-PUNIT_ATOM_DEBUG.cfg"
SRC_URI:append = " file://0031-enable-REGULATOR.cfg"
SRC_URI:append = " file://0032-enable-BRCMFMAC.cfg"
SRC_URI:append = " file://0033-enable-BT_HCIUART_BCM.cfg"
SRC_URI:append = " file://0034-enable-ADS7950.cfg"
SRC_URI:append = " file://0035-enable-ACPI_CONFIGFS.cfg"
SRC_URI:append = " file://0036-enable-SND_SST_ATOM_HIFI2_PLATFORM.cfg"
SRC_URI:append = " file://0037-enable-SND_SOC_SOF-nocodec.cfg"
SRC_URI:append = " file://0038-enable-PHY_TUSB1210.cfg"
SRC_URI:append = " file://0039-enable-USB_CONFIGFS.cfg"
SRC_URI:append = " file://0040-enable-INTEL_MRFLD_ADC.cfg"
SRC_URI:append = " file://0041-enable-EXTCON_INTEL_MRFLD.cfg"
# FIXME: when building 5.13 and above for 32b the stack protector code hangs
# https://lkml.org/lkml/2022/9/29/647
SRC_URI:append = " ${@bb.utils.contains("DEFAULTTUNE", "corei7-32", " file://stack.cfg", "", d)}"

# our additional configs
SRC_URI:append = " file://ftdi_sio.cfg"
SRC_URI:append = " file://ch341.cfg"
SRC_URI:append = " file://smsc95xx.cfg"
SRC_URI:append = " file://bt_more.cfg"
SRC_URI:append = " file://i2c_chardev.cfg"
SRC_URI:append = " file://configfs.cfg"
SRC_URI:append = " file://bridge.cfg"
SRC_URI:append = " file://leds.cfg"
SRC_URI:append = " file://bpf.cfg"
SRC_URI:append = " file://btrfs.cfg"
SRC_URI:append = " file://sof_nocodec.cfg"
SRC_URI:append = " file://audio.cfg"
SRC_URI:append = " file://tun.cfg"
SRC_URI:append = " file://x32.cfg"
SRC_URI:append = " ${@bb.utils.contains("DISTRO_FEATURES", "ppp", " file://ppp.cfg", "", d)}"

# kernel patches
SRC_URI:append = " file://0043b-TODO-driver-core-Break-infinite-loop-when-deferred-p.patch"
SRC_URI:append = " file://0044-REVERTME-usb-dwc3-gadget-skip-endpoints-ep-18-in-out.patch"
SRC_URI:append = " file://0001-mmc-sdhci-Deduplicate-sdhci_get_cd_nogpio.patch"
SRC_URI:append = " file://0002-mmc-sdhci-Remove-unused-prototype-declaration-in-the.patch"
SRC_URI:append = " file://0003-mmc-sdhci-pci-Remove-dead-code-struct-sdhci_pci_data.patch"
SRC_URI:append = " file://0004-mmc-sdhci-pci-Remove-dead-code-cd_gpio-cd_irq-et-al.patch"
SRC_URI:append = " file://0005-mmc-sdhci-pci-Remove-dead-code-rst_n_gpio-et-al.patch"
SRC_URI:append = " file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch"
SRC_URI:append = " file://0001-8250_mid-arm-rx-dma-on-all-ports-with-dma-continousl.patch"
SRC_URI:append = " file://0001a-serial-8250_dma-use-linear-buffer-for-transmit.patch"
SRC_URI:append = " file://0001-serial-8250_port-when-using-DMA-do-not-split-writes-.patch"
SRC_URI:append = " file://0001-tty-tty_io-Switch-to-vmalloc-fallback-in-case-of-TTY.patch"

# usefull kernel debug options here
#SRC_URI:append = " file://0001-8250_mid-toggle-IO7-on-ttyS1-interrupt-entry.patch"

SRCREV ??= "v${PV}"
LINUX_VERSION_EXTENSION = "-edison-acpi-${LINUX_KERNEL_TYPE}"

LINUX_VERSION ?= "${PV}"

COMPATIBLE_MACHINE = "edison"

# This one is necessary too - otherwise the compilation itself fails later.
DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
