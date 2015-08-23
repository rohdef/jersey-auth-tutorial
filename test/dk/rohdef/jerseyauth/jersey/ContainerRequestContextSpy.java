package dk.rohdef.jerseyauth.jersey;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;
import java.io.InputStream;
import java.net.URI;
import java.util.*;

/**
 * License MIT
 *
 * @author Rohde Fischer
 */
public class ContainerRequestContextSpy implements ContainerRequestContext {
    private SecurityContext securityContext = null;
    private MultivaluedHashMap<String, String> headers = new MultivaluedHashMap<>();

    public ContainerRequestContextSpy(String authorization) {
        headers.add("authorization", authorization);
    }

    @Override
    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    @Override
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public MultivaluedMap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String getHeaderString(String key) {
        return headers.getFirst(key);
    }

    /*
     *
     * Everything below this point is currently not under test
     * Thus all values below here is defaults
     *
     */

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public Locale getLanguage() {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public MediaType getMediaType() {
        return null;
    }

    @Override
    public List<MediaType> getAcceptableMediaTypes() {
        return null;
    }

    @Override
    public List<Locale> getAcceptableLanguages() {
        return null;
    }

    @Override
    public Map<String, Cookie> getCookies() {
        return null;
    }

    @Override
    public boolean hasEntity() {
        return false;
    }

    @Override
    public InputStream getEntityStream() {
        return null;
    }

    @Override
    public void setEntityStream(InputStream inputStream) {

    }

    @Override
    public void abortWith(Response response) {

    }

    @Override
    public Object getProperty(String s) {
        return null;
    }

    @Override
    public Collection<String> getPropertyNames() {
        return null;
    }

    @Override
    public void setProperty(String s, Object o) {

    }

    @Override
    public void removeProperty(String s) {

    }

    @Override
    public UriInfo getUriInfo() {
        return null;
    }

    @Override
    public void setRequestUri(URI uri) {

    }

    @Override
    public void setRequestUri(URI uri, URI uri1) {

    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public void setMethod(String s) {

    }
}
