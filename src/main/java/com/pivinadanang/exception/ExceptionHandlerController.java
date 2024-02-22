package com.pivinadanang.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(SQLException ex) {
        ModelAndView modelAndView = new ModelAndView("admin/errors/500");
        modelAndView.addObject("errorMessage", "A database error occurred: " + ex.getMessage());
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CloudinaryException.class)
    public ModelAndView handleCloudinaryException(CloudinaryException ex) {
        ModelAndView mav = new ModelAndView("/admin/errors/cloudinaryError");
        mav.addObject("errorMessage", "failed to load to Cloudinary the image file: " + ex.getMessage());
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return mav;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDataAccessException(DataAccessException ex) {
        ModelAndView modelAndView = new ModelAndView("admin/errors/500");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("admin/errors/404");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;

    }

    @ExceptionHandler(ImageProcessingException.class)
    public ModelAndView handleImageProcessingException(ImageProcessingException ex) {
        ModelAndView modelAndView = new ModelAndView("admin/errors/500"); // Tên của template HTML
        modelAndView.addObject("errorMessage", ex.getMessage()); // Dữ liệu truyền cho template
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleDefaultException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("admin/errors/500");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

}
