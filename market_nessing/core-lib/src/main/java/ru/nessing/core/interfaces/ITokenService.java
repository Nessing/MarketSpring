package ru.nessing.core.interfaces;

import ru.nessing.core.models.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);
}
