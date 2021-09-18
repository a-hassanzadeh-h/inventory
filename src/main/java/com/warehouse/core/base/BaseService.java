package com.warehouse.core.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.app.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;

import java.util.List;

import static org.springframework.security.acls.domain.BasePermission.READ;
import static org.springframework.security.acls.domain.BasePermission.WRITE;

public abstract class BaseService<E extends BaseEntity, R extends BaseRepository<E>> {

    protected final ObjectMapper mapper;
    protected final Logger logger = LoggerFactory.getLogger(BaseService.class);
    protected final R repository;
    protected ApplicationContext context;
    protected MutableAclService aclService;

    public BaseService(ApplicationContext context, R repository) {
        this.context = context;
        this.repository = repository;
        this.mapper = context.getBean(ObjectMapper.class);
        this.aclService = context.getBean(MutableAclService.class);
    }

    public E create(E e) {
        return repository.save(initials(e));
    }

    public E findById(long id) {
        return repository.findById(id).orElseThrow();
    }

    public Page<E> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public E replaceById(long id, E e) {
        return replace(e, findById(id));
    }

    public E deleteById(long id) {
        return delete(findById(id));
    }

    public E delete(E e) {
        repository.delete(e);
        return e;
    }

    //todo validate target before save
    public E replace(E target, E source) {
        return repository.save(defaults(target, source));
    }

    public E defaults(E target, E source) {
        target.id = source.id;
        target.created = source.created;
        target.updated = source.updated;
        return target;
    }

    public E initials(E e) {
        e.id = 0L;
        e.created = null;
        e.updated = null;
        return e;
    }

    public E serialize(E e) {
        return e;
    }

    public E deserialize(E e) {
        return mapper.convertValue(e,(Class<? extends E>) e.getClass());
    }

    public void grantPermission(String principal, E e, Permission[] permissions) {
        logger.info("Grant {} permission to {} principal on {}", permissions, principal, e.getId().getClass());

        ObjectIdentity oi = new ObjectIdentityImpl(Product.class, e.getId());

        Sid sid = new PrincipalSid(principal);

        MutableAcl acl;

        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (NotFoundException exception) {
            acl = aclService.createAcl(oi);
        }
        for (Permission permission : permissions) {
            switch (permission) {
                case READ:
                    acl.insertAce(acl.getEntries().size(), READ, sid, true);
                    break;
                case WRITE:
                    acl.insertAce(acl.getEntries().size(), WRITE, sid, true);

            }
        }
        aclService.updateAcl(acl);
    }

    private enum Permission {
        READ,
        WRITE
    }
}
