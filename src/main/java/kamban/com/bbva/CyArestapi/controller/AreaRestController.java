package kamban.com.bbva.CyArestapi.controller;

import kamban.com.bbva.CyArestapi.model.MDLArea;
import kamban.com.bbva.CyArestapi.model.MDLError;
import kamban.com.bbva.CyArestapi.model.MDLUser;
import kamban.com.bbva.CyArestapi.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area/api/v2")
public class AreaRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplinaRestController.class);

    @Autowired
    private AreaService _areaService;

    @PostMapping("/")
    public ResponseEntity<Object> createArea(@RequestBody MDLArea areaData){
        try{
            if(areaData!=null && areaData.getCode()!=null){
                if(_areaService.existArea(areaData.getCode())){
                    return new ResponseEntity<Object>("Ya existe el area "+areaData.getCode(), HttpStatus.ALREADY_REPORTED);
                }else{
                    String idResult= _areaService.createArea(areaData);
                    if(idResult!=null) {
                        return new ResponseEntity<Object>(idResult, HttpStatus.CREATED);
                    }else {
                        return new ResponseEntity<Object>(new MDLError("No ha sido posible crear el area"), HttpStatus.NOT_MODIFIED);
                    }
                }
            }else {
                return new ResponseEntity<Object>(new MDLError("No se identifico un area por crear"), HttpStatus.NOT_MODIFIED);
            }

        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateArea(@RequestBody MDLArea areaData){
        try{
            if(areaData!=null && areaData.getCode()!=null){
                if(_areaService.existArea(areaData.getCode())){
                    String idResult= _areaService.alterArea(areaData);
                    if(idResult!=null) {
                        return ResponseEntity.status(HttpStatus.OK).body(idResult);
                    }else {
                        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo modificar");
                    }
                }else{
                    return new ResponseEntity<Object>("No existe el area "+areaData.getCode(), HttpStatus.ALREADY_REPORTED);
                }
            }else {
                return new ResponseEntity<Object>("No se han identificado datos a procesar", HttpStatus.ALREADY_REPORTED);
            }

        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<MDLArea>> getAll(){
        try {
            List<MDLArea> listArea=_areaService.retrieveAllArea();
            if(listArea!=null && listArea.size()>0){
                return new ResponseEntity<List<MDLArea>>(listArea,HttpStatus.OK);
            }else{
                return new ResponseEntity<List<MDLArea>>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<MDLArea> getByCode(@PathVariable String code){
        try {
            MDLArea area=_areaService.retrieveAreaByCode(code);
            if(area!=null){
                return new ResponseEntity<MDLArea>(area,HttpStatus.OK);
            }else{
                return new ResponseEntity<MDLArea>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<MDLArea>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/{code}")
    public ResponseEntity<Object> disableArea(@PathVariable String id,@PathVariable String code){
        try {
            MDLArea area=_areaService.retrieveAreaByCode(code);
            if(area!=null){
                if(_areaService.disableArea(id)){
                    return new ResponseEntity<Object>("Area modificada",HttpStatus.OK);
                }else{
                    return new ResponseEntity<Object>("No se pudo modificar el area",HttpStatus.NOT_MODIFIED);
                }

            }else{
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
