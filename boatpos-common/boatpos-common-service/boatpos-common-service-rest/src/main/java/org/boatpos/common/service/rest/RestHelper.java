package org.boatpos.common.service.rest;

import com.google.common.io.Files;
import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Optional;

/**
 * Helper for some rest-specific methods.
 */
@Dependent
public class RestHelper {

    @Inject
    @SLF4J
    private LogWrapper log;

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

    /**
     * Create the response based on a zip-file.
     *
     * @param file the zip-file
     * @return the response
     */
    public Response createZipOutput(File file) {
        try {
            return Response
                    .ok(Files.toByteArray(file))
                    .type("application/zip")
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
