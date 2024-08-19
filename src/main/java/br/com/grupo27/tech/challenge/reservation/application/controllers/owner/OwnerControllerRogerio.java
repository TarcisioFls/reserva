//package br.com.grupo27.tech.challenge.reservation.application.controllers.owner;
//
//import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.request.OwnerRequest;
//import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.response.OwnerListResponse;
//import br.com.grupo27.tech.challenge.reservation.application.controllers.owner.response.OwnerResponse;
//import br.com.grupo27.tech.challenge.reservation.infra.model.OwnerModel;
//import br.com.grupo27.tech.challenge.reservation.infra.repository.owner.OwnerRepository;
//import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import static org.springframework.http.HttpStatus.NO_CONTENT;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/owners")
//public class OwnerControllerRogerio {
//
//    private final OwnerRepository ownerRepository;
//    private final ModelMapper mapper;
//
//    @PostMapping
//    public ResponseEntity<OwnerResponse> create(@RequestBody OwnerRequest request) {
//        OwnerModel ownerModel = mapper.map(request, OwnerModel.class);
//        ownerRepository.save(ownerModel);
//        OwnerResponse ownerResponse = mapper.map(ownerModel, OwnerResponse.class);
//        return ResponseEntity.ok(ownerResponse);
//    }
//
//    @GetMapping
//    public ResponseEntity<OwnerListResponse> getAll() {
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OwnerResponse> getById(Long id) {
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<OwnerResponse> update(Long id, @RequestBody OwnerRequest request) {
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(NO_CONTENT)
//    public void delete(Long id) {
//    }
//
//}
