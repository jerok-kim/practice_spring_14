package kim.jerok.practice_spring_14.controller;

import kim.jerok.practice_spring_14.dto.ResponseDto;
import kim.jerok.practice_spring_14.model.Customer;
import kim.jerok.practice_spring_14.model.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 1. 요청 DTO는 나중에 배움
 * 2. Service도 나중에 배움 (트랜잭션 처리 commit, rollback)
 * 3. 이번장 목표 : Repository와 ORM
 * 4. 숙제 Controller 테스트 코드, Repository 테스트 코드 작성
 */
@RestController
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Customer customerPS = customerRepository.findById(id);
        ResponseDto<?> dto = new ResponseDto<>("한건 조회 성공", customerPS);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page) {
        List<Customer> customerListPS = customerRepository.findAll(page);
        ResponseDto<?> dto = new ResponseDto<>("전체 조회 성공", customerListPS);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer) {
        // 1. 존재 여부 확인 : 최소한의 트랜잭션 발동 and Setter 만들지 않기
        Customer customerPS = customerRepository.findById(id);

        // 2. 객체 값 변경
        customerPS.update(customer.getName(), customer.getTel());

        // 3. 변경된 객체로 DB 수정
        customerRepository.update(customerPS);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // 1. 존재 여부 확인 : 최소한의 DB 트래픽
        customerRepository.findById(id);

        // 2. 존재하면 삭제
        customerRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
