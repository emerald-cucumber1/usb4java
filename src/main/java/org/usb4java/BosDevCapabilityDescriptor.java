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
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * A generic representation of a BOS Device Capability descriptor.
 * <p>
 * It is advised to check bDevCapabilityType and call the matching
 * get*Descriptor method to get a structure fully matching the type.
 *
 * @author Klaus Reimer (k@ailis.de)
 */
@EqualsAndHashCode(doNotUseGetters = true)
public final class BosDevCapabilityDescriptor {

    /**
     * The native pointer to the descriptor structure.
     */
    private long bosDevCapabilityDescriptorPointer;

    /**
     * Package-private constructor to prevent manual instantiation. BOS device
     * capability descriptors are always created by JNI.
     */
    BosDevCapabilityDescriptor() {
        // Empty
    }

    /**
     * Returns the native pointer.
     *
     * @return The native pointer.
     */
    public long getPointer() {
        return this.bosDevCapabilityDescriptorPointer;
    }

    /**
     * Returns the size of this descriptor (in bytes).
     *
     * @return The descriptor size in bytes;
     */
    public native byte bLength();

    /**
     * Returns the descriptor type.
     *
     * @return The descriptor type.
     */
    public native byte bDescriptorType();

    /**
     * Returns the device capability type.
     *
     * @return The device capability type.
     */
    public native byte bDevCapabilityType();

    /**
     * Returns the device capability data (bLength - 3 bytes).
     *
     * @return The device capability data.
     */
    public native ByteBuffer devCapabilityData();

    /**
     * Returns a dump of this descriptor.
     *
     * @return The descriptor dump.
     */
    public String dump() {
        return String.format(
                "BOS Device Capability Descriptor:%n" +
                        "  bLength %18d%n" +
                        "  bDescriptorType %10d%n" +
                        "  bDevCapabilityType %7s%n" +
                        "  devCapabilityData:%n%s%n",
                this.bLength() & 0xFF,
                this.bDescriptorType() & 0xFF,
                this.bDevCapabilityType() & 0xFF,
                DescriptorUtils.dump(this.devCapabilityData())
                        .replaceAll("(?m)^", "    "));
    }

    @Override
    public @NotNull String toString() {
        return this.dump();
    }
}
