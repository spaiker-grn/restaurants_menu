package com.github.spaiker_grn.restaurants_menu.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "myBeanApi",
        version = "v1",
        resource = "myBean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.restaurants_menu.spaiker_grn.github.com",
                ownerName = "backend.restaurants_menu.spaiker_grn.github.com",
                packagePath = ""
        )
)
public class MyBeanEndpoint {

    private static final Logger logger = Logger.getLogger(MyBeanEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(MyBean.class);
    }

    /**
     * Returns the {@link MyBean} with the corresponding ID.
     *
     * @param name the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code MyBean} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "myBean/{name}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public MyBean get(@Named("name") String name) throws NotFoundException {
        logger.info("Getting MyBean with ID: " + name);
        MyBean myBean = ofy().load().type(MyBean.class).id(name).now();
        if (myBean == null) {
            throw new NotFoundException("Could not find MyBean with ID: " + name);
        }
        return myBean;
    }



    @ApiMethod(
            name = "insertFromWeb")
    public void insertFromWeb(@Named("name") String name, @Named("description") String description, @Named("imageSource") String imageSource) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that myBean.name has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        MyBean myBean = new MyBean(name, description, imageSource);
        ofy().save().entity(myBean).now();
        logger.info("Created MyBean with ID: " + myBean.getName());

    }


    /**
     * Inserts a new {@code MyBean}.
     */
    @ApiMethod(
            name = "insert",
            path = "myBean",
            httpMethod = ApiMethod.HttpMethod.POST)
    public MyBean insert(MyBean myBean) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that myBean.name has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(myBean).now();
        logger.info("Created MyBean with ID: " + myBean.getName());

        return ofy().load().entity(myBean).now();
    }

    /**
     * Updates an existing {@code MyBean}.
     *
     * @param name   the ID of the entity to be updated
     * @param myBean the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code name} does not correspond to an existing
     *                           {@code MyBean}
     */
    @ApiMethod(
            name = "update",
            path = "myBean/{name}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public MyBean update(@Named("name") String name, MyBean myBean) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(name);
        ofy().save().entity(myBean).now();
        logger.info("Updated MyBean: " + myBean);
        return ofy().load().entity(myBean).now();
    }

    /**
     * Deletes the specified {@code MyBean}.
     *
     * @param name the ID of the entity to delete
     * @throws NotFoundException if the {@code name} does not correspond to an existing
     *                           {@code MyBean}
     */
    @ApiMethod(
            name = "remove",
            path = "myBean/{name}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("name") String name) throws NotFoundException {
        checkExists(name);
        ofy().delete().type(MyBean.class).id(name).now();
        logger.info("Deleted MyBean with ID: " + name);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "myBean",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<MyBean> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<MyBean> query = ofy().load().type(MyBean.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<MyBean> queryIterator = query.iterator();
        List<MyBean> myBeanList = new ArrayList<MyBean>(limit);
        while (queryIterator.hasNext()) {
            myBeanList.add(queryIterator.next());
        }
        return CollectionResponse.<MyBean>builder().setItems(myBeanList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(String name) throws NotFoundException {
        try {
            ofy().load().type(MyBean.class).id(name).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find MyBean with ID: " + name);
        }
    }
}