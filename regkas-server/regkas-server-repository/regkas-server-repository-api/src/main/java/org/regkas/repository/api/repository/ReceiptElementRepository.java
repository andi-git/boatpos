package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.DomainModelRepository;
import org.regkas.repository.api.builder.AddressBuilder;
import org.regkas.repository.api.builder.ReceiptElementBuilder;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.model.ReceiptElement;

/**
 * The repository for the {@link ReceiptElement}.
 */
public interface ReceiptElementRepository extends DomainModelRepository<ReceiptElement, ReceiptElementBuilder> {

}
