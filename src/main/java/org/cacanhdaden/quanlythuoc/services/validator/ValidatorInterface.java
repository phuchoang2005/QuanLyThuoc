package org.cacanhdaden.quanlythuoc.services.validator;

import org.cacanhdaden.quanlythuoc.services.Exception.InvalidInformationException;

public interface ValidatorInterface {
    void checkValid() throws InvalidInformationException;
}
