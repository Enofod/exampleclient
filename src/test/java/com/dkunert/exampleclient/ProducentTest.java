package com.dkunert.exampleclient;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner()
public class ProducentTest {

    @Autowired
    private StubFinder stubFinder;

    @Test
    public void checkStub() {
        String stubUrl = stubFinder.findStubUrl("com.dkunert", "exampleproducent").toString();

        System.out.println(stubUrl);

        List<String> tallPeople = Arrays.asList(new RestTemplate().getForObject(stubUrl + "/people/tall", String[].class));

        Assertions.assertThat(tallPeople.size()).isEqualTo(2);
        tallPeople.forEach(System.out::println);
    }

}
