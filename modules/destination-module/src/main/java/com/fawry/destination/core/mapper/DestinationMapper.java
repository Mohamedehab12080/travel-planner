package com.fawry.destination.core.mapper;

import com.fawry.destination.model.dto.DestinationDTO;
import com.fawry.destination.model.entity.Destination;
import com.fawry.destination.model.vto.AdminDestinationVTO;
import com.fawry.destination.model.vto.DestinationListItemVTO;
import com.fawry.destination.model.vto.DestinationVTO;
import com.fawry.user.model.entity.User;
import com.fawry.user.model.vto.LightUserVTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DestinationMapper {

    Destination toEntity(DestinationDTO requestDTO);

    List<Destination> toEntities(List<DestinationDTO> destinations);

    DestinationVTO toDestinationVTO(Destination destination);

    List<DestinationVTO> toDestinationsVTOs(List<Destination> destinations);

    DestinationListItemVTO toDestinationListItemVTO(Destination destination);

    List<DestinationListItemVTO> toDestinationListItemVTOs(List<Destination> destination);

    AdminDestinationVTO toAdminDestinationVTO(Destination destination);

    LightUserVTO toLightUserVTO(User user);

}