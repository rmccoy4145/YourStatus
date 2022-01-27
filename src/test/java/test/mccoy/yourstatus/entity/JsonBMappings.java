package test.mccoy.yourstatus.entity;

import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import org.eclipse.yasson.JsonBindingProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.time.LocalDateTime;

class JsonBMappings {

    static User userAStub = new User();
    static User userBStub = new User();

    static Jsonb mapper;

    @BeforeAll
    static void beforeAll() {
        mapper = JsonbBuilder.newBuilder(new JsonBindingProvider()).build();
        userAStub.setId(1L);
        userAStub.setUserName("UserA");
        userBStub.setId(2L);
        userBStub.setUserName("UserB");

    }

    @Test
    void ValidFollowPojo_InvokeJsonBMapper_ValidJsonString() {
        //when
        UserFollow userFollowStub = new UserFollow();
        userFollowStub.setId(1L);
        userFollowStub.setFollowUser(userAStub);
        userFollowStub.setUser(userBStub);

        //given
        mapper = JsonbBuilder.create();
        String jsonString = mapper.toJson(userFollowStub);

        //then
        Assertions.assertTrue(jsonString.equals("{\"followUserId\":1,\"id\":1,\"userId\":2}"));
    }

    @Test
    void ValidStatusPojo_InvokeJsonBMapper_ValidJsonString() {
        //when
        UserStatusMessage userStatusMessage = new UserStatusMessage();
        userStatusMessage.setId(1L);
        userStatusMessage.setUser(userAStub);
        userStatusMessage.setDatetime(LocalDateTime.now());
        userStatusMessage.setMessage("This is a test message");

        //given
        mapper = JsonbBuilder.create();
        String jsonString = mapper.toJson(userStatusMessage);

//        System.out.println(jsonString);
        //then
        Assertions.assertTrue(jsonString.equals("{\"datetime\":\"2022-01-27T15:59:04.2894409\",\"id\":1,\"message\":\"This is a test message\",\"userId\":1}"));
    }

    @Test
    void ValidUserPojo_InvokeJsonBMapper_ValidJsonString() {
        //when
        //User stubbed in beforeAll()

        //given
        mapper = JsonbBuilder.create();
        String jsonString = mapper.toJson(userAStub);

        //then
        Assertions.assertTrue(jsonString.equals("{\"id\":1,\"userName\":\"UserA\"}"));
    }
}