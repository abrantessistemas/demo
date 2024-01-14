package br.com.empresa.demo.dto;

import br.com.empresa.demo.configuration.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
