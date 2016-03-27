package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.DomainModelRepositoryCore;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.repository.api.builder.ReceiptElementBuilder;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.repository.ReceiptElementRepository;
import org.regkas.repository.core.builder.ReceiptElementBuilderCore;
import org.regkas.repository.core.model.ReceiptElementCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptElementRepositoryCore extends DomainModelRepositoryCore<ReceiptElement, ReceiptElementCore, ReceiptElementEntity, ReceiptElementBuilder, ReceiptElementBuilderCore> implements ReceiptElementRepository {

}
