package me.jastz.app.mini.session;

import java.util.UUID;

/**
 * @author zhiwen
 */
public class SessionIdFactory {

    public static String createSessionId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
