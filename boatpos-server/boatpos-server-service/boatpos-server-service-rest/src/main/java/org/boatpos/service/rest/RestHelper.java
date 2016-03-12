package org.boatpos.service.rest;

import org.boatpos.common.service.api.bean.AbstractBean;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Helper for some rest-specific methods.
 */
@Dependent
public class RestHelper {

    /**
     * Create a {@link Response} with state {@link Response.Status#OK} when the assigned {@link Optional#isPresent()} is
     * {@code true}. Otherwise the method returns an empty {@link Response} with {@link Response.Status#NOT_FOUND}.
     *
     * @param bean the {@link Optional} of the bean
     * @param <T>  the type of the bean (a subclass id {@link AbstractBean})
     * @return the {@link Response} for the rest-service to return
     */
    public <T extends AbstractBean> Response responseOkOrNotFound(Optional<T> bean) {
        if (bean.isPresent()) {
            return Response.ok(bean.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Create a {@link Response} where the entity is the assigned bean and status is {@link Response.Status#CREATED}
     *
     * @param bean the {@link AbstractBean}
     * @return the {@link Response} for the rest-service to return
     */
    public Response responseCreated(AbstractBean bean) {
        return Response
                .status(Response.Status.CREATED)
                .entity(bean)
                .build();
    }
}
