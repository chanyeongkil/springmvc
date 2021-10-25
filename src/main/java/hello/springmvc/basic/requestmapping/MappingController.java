package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */

    @GetMapping(value = "/hello-basic-get")
    public String helloBasicGet(){
        log.info("helloBasic");
        return "get";
    }

    @PostMapping(value = "/hello-basic-post")
    public String helloBasicPost(){
        log.info("helloBasic");
        return "post";
    }

    /**
     * URL 경로에 변수 사용 시  @PathVariable 사용 (변수명이 같을 경우 생략 가능)
     * */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userid){
        log.info("@PathVariable userId = {}", userid);
        return "ok";
    }

    @GetMapping("/mapping2/{userId}")
    public String mappingPath2(@PathVariable String userId){
        log.info("@PathVariable userId = {}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     * */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     * */
    @GetMapping(value = "/mapping-param", params = "mode=debug") public String mappingParam() {
        log.info("Mapping - Param");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     * */
    @GetMapping(value = "/mapping-header", headers = "mode=debug") public String mappingHeader() {
        log.info("Mapping - Header");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type  * consumes="application/json"
     * consumes="!application/json"  * consumes="application/*"
     * consumes="*\/*"
     * "application/json" == MediaType.APPLICATION_JSON_VALUE (Spring에서 ENUM 형태로 정의된코드가 있다.)
     * */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE) public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * */
    @PostMapping(value = "/mapping-produce", produces = "text/html") public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
