package com.panvdev.apirest_prueba.controladores;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panvdev.apirest_prueba.respuestas.ApiResponse;
import com.panvdev.apirest_prueba.servicios.DisccountServiceImplements;
import com.panvdev.apirest_prueba.excepciones.ApiRequestException;
import com.panvdev.apirest_prueba.modelos.Category;
import com.panvdev.apirest_prueba.modelos.Disccount;
import com.panvdev.apirest_prueba.modelos.Product;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:2009")
public class DisscountController {
	@Autowired
	DisccountServiceImplements discountService;
	

	@GetMapping("/disccounts")
    public ResponseEntity<ApiResponse> getCategories() {
		 try {
	            List<Disccount> discounts = discountService.getDiscounts();
	            
	            ApiResponse response = ApiResponse.success("Listado de descuentos", discounts);
	            return ResponseEntity.ok(response);
	        } catch (Exception ex) {
	            ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	        }
    }
	
	@GetMapping("/disccount/{id}")
	public ResponseEntity<ApiResponse> getDiscountId(@PathVariable long id) {
		try {
			Disccount discount = discountService.GetById(id);
            ApiResponse response = ApiResponse.success("Listado de descuentos", discount);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@PostMapping("/disccount")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Disccount dis) {
		try {
			
			Disccount newDis = discountService.save(dis);
			ApiResponse response = ApiResponse.success("Descuento creado correctamente", newDis);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@DeleteMapping("/disccount/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		try {
			discountService.eliminar(id);
			ApiResponse response = ApiResponse.success("Descuento eliminado correctamente",null);
	        return ResponseEntity.ok(response); 
		} catch (ApiRequestException ex) {
			ApiResponse errorResponse = ApiResponse.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@PutMapping("/disccount/{id}")
	public ResponseEntity<ApiResponse> updateUserId(@PathVariable long id, @RequestBody Map<String, String> requestBody) {
		try {
			 //Long catId = Long.parseLong(discountService.get(id));
			Disccount disFound = discountService.GetById(id);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (disFound == null) {
	            throw new ApiRequestException("Descuento no encontrado", HttpStatus.NOT_FOUND);
	        }
			disFound.setDescription(requestBody.get("description"));
			disFound.setDiscount_code(requestBody.get("discount_code"));
			disFound.setDiscount_percentage( new BigDecimal(requestBody.get("discount_percentage")));
			disFound.setMin_purchase(new BigDecimal(requestBody.get("getMin_purchase")));
			disFound.setEnd_date(  sdf.parse(requestBody.get("end_date")) );
			disFound.setStart_date(  sdf.parse(requestBody.get("start_date")) );
			
			Disccount updatedDiscount =discountService.save(disFound);
			ApiResponse response = ApiResponse.success("Descuento actualizado correctamente",updatedDiscount);
	        return ResponseEntity.ok(response); 
		} catch (ApiRequestException e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			ApiResponse errorResponse = ApiResponse.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
}
