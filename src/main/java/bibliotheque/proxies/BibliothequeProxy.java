package bibliotheque.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "bibliocapi", url = "localhost:9090")
public interface BibliothequeProxy {

    @GetMapping("/lister-retard")
    List<String> listerMail();

}