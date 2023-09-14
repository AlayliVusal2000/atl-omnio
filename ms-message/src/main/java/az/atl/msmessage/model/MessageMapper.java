//package az.atl.msmessage.model;
//
//
//import az.atl.msmessage.dao.entity.MessageEntity;
//
//import java.util.List;
//
//public enum MessageMapper {
//    PRODUCT_MAPPER;
//
//    public static List<MessageDto> buildDtoList(List<MessageEntity> entityList) {
//        return entityList.stream().map(MessageMapper::buildDto).toList();
//    }
//
//    public static MessageDto buildDto(MessageEntity entity) {
//        return MessageDto.builder()
//                .id(entity.getId())
//                .message(entity.getMessage()).build();
////                .price(entity.getPrice())
////                .count(entity.getCount()).build();
//    }
//}
