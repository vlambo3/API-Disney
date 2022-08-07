package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if(filtersDTO.getGenreId() != null) {
                predicates.add(
                                criteriaBuilder.equal(root.get("idGenre"), filtersDTO.getGenreId())
                );
            }

            //Order resolver
            String orderByFieldNames = "creationDate";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByFieldNames)):
                            criteriaBuilder.desc(root.get(orderByFieldNames))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}
