//package com.hw.admin.controller;
//
//import com.hw.admin.exception.BusinessException;
//import org.apache.commons.collections4.MapUtils;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//@RestController
//@RequestMapping("/attachments")
//public class AttachmentController {
//    /**
//     * 上传附件
//     *
//     * @param type             附件类别
//     * @param multipartRequest 附件
//     * @return 上传成功的附件
//     */
//    @PostMapping(path = "/{type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ApiOut<List<AttachmentVO>> upload(@PathVariable("type") String type,
//                                             MultipartHttpServletRequest multipartRequest) {
//        if (null == multipartRequest || MapUtils.isEmpty(multipartRequest.getFileMap())) {
//            throw new BusinessException("上传的文件不正确");
//        }
//
//        String customMessage = multipartRequest.getParameter("customMessage");
//        return ApiOut.newSuccessResponse(toVos(attachmentService
//                .upload(attachmentType, buildMultipartFiles(multipartRequest)), customMessage));
//    }
//}
