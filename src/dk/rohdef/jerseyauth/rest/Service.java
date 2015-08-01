package dk.rohdef.jerseyauth.rest;

import dk.rohdef.jerseyauth.model.ReturnData;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * A dummy service secured by a login
 *
 * License MIT
 * @author Rohde Fischer
 */
@Path("/service")
@PermitAll
public class Service {
    @GET
    @Path("/answer")
    @RolesAllowed("admin")
    public ReturnData getAnswer() {
        return new ReturnData("Hitchhikers Guide To The Galaxy", "The Answer to the Ultimate Question of Life," +
                " The Universe, and Everything", 42);
    }

    @GET
    @Path("/valuables")
    @RolesAllowed("user")
    public ReturnData getValuables() {
        return new ReturnData("Ruth Benedict", "The trouble with life\n" +
                "isn't that there is no answer,\n" +
                "but that there are so many answers", 59866);
    }

    @GET
    @Path("/medicine")
    @RolesAllowed({"admin","user"})
    public ReturnData getDual() {
        return new ReturnData("Tim Minchin", "You know what they call alternative medicine that's been proved to work? " +
                "- Medicine.", 1);
    }

    @GET
    @Path("/funny")
    @PermitAll
    public ReturnData getJoke() {
        return new ReturnData("Thud!", "Vimes had never got on with any game much more complex than darts. " +
                "Chess in particular had always annoyed him. It was the dumb way the pawns went off and slaughtered " +
                "their fellow pawns while the kings lounged about doing nothing that always got to him; if only the " +
                "pawns united, maybe talked the rooks around, the whole board could’ve been a republic in a dozen moves.",
                34);
    }
}
