package ro.itschool.homework_project_0102.mapper;

public interface ObjectMapper <T, R> {
    T mapToDto (R r);
    R mapToEntity(T t);
}
