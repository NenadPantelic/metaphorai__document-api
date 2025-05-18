package ai.metaphor.document_api.controller;

import ai.metaphor.document_api.dto.request.DocumentFilterRequest;
import ai.metaphor.document_api.dto.response.DocumentItemResponse;
import ai.metaphor.document_api.dto.response.DocumentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    @PostMapping
    List<DocumentItemResponse> filterDocuments(@RequestBody DocumentFilterRequest documentFilterRequest) {
        return null;
    }

    @GetMapping("/{documentId}")
    DocumentResponse getDocument(@PathVariable("documentId") String documentId) {
        return null;
    }
}
