package com.fazliddin.appauthservice.security.oauth2;

import com.fazliddin.appauthservice.security.oauth2.user.OAuth2UserInfo;
import com.fazliddin.appauthservice.security.oauth2.user.OAuth2UserInfoFactory;
import com.fazliddin.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        System.out.println(new Timestamp(System.currentTimeMillis()));
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

//        switch (oAuth2UserRequest.getClientRegistration().getRegistrationId()) {
//            case "google": {
//                User user = userRepository.findByEmail(oAuth2UserInfo.getEmail())
//                        .orElseThrow(() -> RestException.restThrow("Please sign up before", HttpStatus.UNAUTHORIZED));
//                return new UserPrincipal(user, oAuth2User.getAttributes());
//            }
//            case "facebook": {
//                User user = userRepository.findByEmail(oAuth2UserInfo.getEmail())
//                        .orElseThrow(() -> RestException.restThrow("Please sign up before", HttpStatus.UNAUTHORIZED));
//                return new UserPrincipal(user, oAuth2User.getAttributes());
//            }
//            case "github": {
//                User user = userRepository.findByGithubLogin(oAuth2UserInfo.getLogin())
//                        .orElseThrow(() -> RestException.restThrow("Please sign up before", HttpStatus.UNAUTHORIZED));
//                return new UserPrincipal(user, oAuth2User.getAttributes());
//            }
//            default:
                return null;
        }

}
