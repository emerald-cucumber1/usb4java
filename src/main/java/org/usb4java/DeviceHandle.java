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

/**
 * Structure representing a handle on a USB device.
 * <p>
 * This is an opaque type for which you are only ever provided with a pointer,
 * usually originating from {@link LibUsb#open(Device, DeviceHandle)}.
 * <p>
 * A device handle is used to perform I/O and other operations. When finished
 * with a device handle, you should call {@link LibUsb#close(DeviceHandle)}.
 *
 * @author Klaus Reimer (k@ailis.de)
 */
@EqualsAndHashCode(doNotUseGetters = true)
public final class DeviceHandle {

    /**
     * The native pointer to the device handle structure.
     */
    private long deviceHandlePointer;

    /**
     * Constructs a new device handle. Must be passed to
     * {@link LibUsb#open(Device, DeviceHandle)} before passing it to any
     * other method.
     */
    @Contract(pure = true)
    public DeviceHandle() {
        // Empty
    }

    /**
     * Returns the native pointer to the device handle structure.
     *
     * @return The native pointer to the device handle structure.
     */
    @Contract(pure = true)
    public long getPointer() {
        return this.deviceHandlePointer;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return String.format("libusb device handle 0x%x", this.deviceHandlePointer);
    }
}
