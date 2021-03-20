package zyd.datacenter.Controllers.Asset;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/asset")
@Api(value = "资源包接口", tags="资源包接口")
public class AssetController {
}
