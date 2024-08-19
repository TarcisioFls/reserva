package br.com.grupo27.tech.challenge.reservation.application.controllers.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
public class PageResponse {

    private Pageable pageable;

    private boolean last;

    private int totalElements;

    private int totalPages;

    private int size;

    private int number;

    private Sort sort;

    private boolean first;

    private int numberOfElements;

    private boolean empty;
}
