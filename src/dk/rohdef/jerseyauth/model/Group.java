package dk.rohdef.jerseyauth.model;

/**
 * A simple set of user groups, here a user is either an admin or an user and that's it. In larger systems with
 * dynamic group access etc. this would probably be database controlled. This is out of scope for this guide, but
 * should be doable using the same concepts.
 *
 * License MIT
 * @author Rohde Fischer
 */
public enum Group {
    ADMIN,
    USER
}
