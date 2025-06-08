package am.martirosyan.dormru.mapper;
public interface Mapper<Entity, RequestDto, ResponseDto> {

    Entity toEntity(RequestDto dto);

    ResponseDto toDto(Entity entity);
}
