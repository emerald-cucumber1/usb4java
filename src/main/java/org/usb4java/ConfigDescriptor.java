/*
 * Copyright 2013 Klaus Reimer <k@ailis.de>
 * See LICENSE.md for licensing information.
 *
 * Based on libusb <http://libusb.info/>:
 *
 * Copyright 2001 Johannes Erdfelt <johannes@erdfelt.com>
 * Copyright 2007-2009 Daniel Drake <dsd@gentoo.org>
 * Copyright 2010-2012 Peter Stuge <peter@stuge.se>
 * Copyright 2008-2013 Nathan Hjelm <hjelmn@users.sourceforge.net>
 * Copyright 2009-2013 Pete Batard <pete@akeo.ie>
 * Copyright 2009-2013 Ludovic Rousseau <ludovic.rousseau@gmail.com>
 * Copyright 2010-2012 Michael Plante <michael.plante@gmail.com>
 * Copyright 2011-2013 Hans de Goede <hdegoede@redhat.com>
 * Copyright 2012-2013 Martin Pieuchot <mpi@openbsd.org>
 * Copyright 2012-2013 Toby Gray <toby.gray@realvnc.com>
 */

package org.usb4java;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * A structure representing the standard USB configuration descriptor.
 * <p>
 * This descriptor is documented in section 9.6.3 of the USB 3.0 specification.
 * All multiple-byte fields are represented in host-endian format.
 *
 * @author Klaus Reimer (k@ailis.de)
 */
@EqualsAndHashCode(doNotUseGetters = true)
public final class ConfigDescriptor {

    /**
     * The native pointer to the descriptor structure.
     */
    private long configDescriptorPointer;

    /**
     * Constructs a new config descriptor which can be passed to the
     * {@link LibUsb#getConfigDescriptor(Device, byte, ConfigDescriptor)}
     * method.
     */
    @Contract(pure = true)
    public ConfigDescriptor() {
        // Empty
    }

    /**
     * Returns the native pointer.
     *
     * @return The native pointer.
     */
    public long getPointer() {
        return this.configDescriptorPointer;
    }

    /**
     * Returns the size of this descriptor (in bytes).
     *
     * @return The size of this descriptor (in bytes).
     */
    public native byte bLength();

    /**
     * Returns the descriptor type. Will have value {@link LibUsb#DT_CONFIG}
     * in this context.
     *
     * @return The descriptor type.
     */
    public native byte bDescriptorType();

    /**
     * Returns the total length of data returned for this configuration.
     *
     * @return The total length of data.
     */
    public native short wTotalLength();

    /**
     * Returns the number of interfaces supported by this configuration.
     *
     * @return The number of supported interfaces.
     */
    public native byte bNumInterfaces();

    /**
     * Returns the identifier value for this configuration.
     *
     * @return The identifier value.
     */
    public native byte bConfigurationValue();

    /**
     * Returns the index of string descriptor describing this configuration.
     *
     * @return The string descriptor index.
     */
    public native byte iConfiguration();

    /**
     * Returns the configuration characteristics.
     *
     * @return The configuration characteristics.
     */
    public native byte bmAttributes();

    /**
     * Returns the maximum power consumption of the USB device from this bus
     * configuration when the device is fully operation. Expressed in units
     * of 2 mA when the device is operating in high-speed mode and in units
     * of 8 mA when the device is operating in super-speed mode.
     *
     * @return The maximum power consumption.
     */
    public native byte bMaxPower();

    /**
     * Returns the array with interfaces supported by this configuration.
     *
     * @return The array with interfaces.
     */
    public native Interface[] iface();

    /**
     * Extra descriptors.
     * <p>
     * If libusb encounters unknown interface descriptors, it will store them
     * here, should you wish to parse them.
     *
     * @return The extra descriptors.
     */
    public native ByteBuffer extra();

    /**
     * Length of the extra descriptors, in bytes.
     *
     * @return The extra descriptors length.
     */
    public native int extraLength();

    /**
     * Returns a dump of this descriptor.
     *
     * @return The descriptor dump.
     */
    public String dump() {
        final StringBuilder builder = new StringBuilder();

        builder.append(String.format(
                "%s" +
                        "  extralen %17d%n" +
                        "  extra:%n" +
                        "%s",
                DescriptorUtils.dump(this),
                this.extraLength(),
                DescriptorUtils.dump(this.extra()).replaceAll("(?m)^", "    ")));

        for (final Interface iface : this.iface()) {
            builder.append(String.format("%n")).append(iface.dump());
        }

        return builder.toString();
    }

    @Override
    public @NotNull String toString() {
        return this.dump();
    }
}
