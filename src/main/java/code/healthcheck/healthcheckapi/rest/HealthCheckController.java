package code.healthcheck.healthcheckapi.rest;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

	@GetMapping
	public ResponseEntity<Status> getStatus(@RequestParam String format) {
		
		if (format.equals("short")) {
			return ResponseEntity.ok(new Status("OK"));
		} else if (format.equals("full")) {
			return ResponseEntity.ok(new Status("OK", ZonedDateTime.now()));
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

	public class Status {

		private String status;

		@JsonInclude(Include.NON_NULL)
		private String currentTime;

		Status(String status) {
			this.status = status;

		}

		Status(String status, ZonedDateTime time) {
			this.status = "OK";

			this.currentTime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCurrentTime() {
			return currentTime;
		}

		public void setCurrentTime(String currentTime) {
			this.currentTime = currentTime;
		}

	}
}
