package com.taller.nivelacion.competencia.config;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
            + "MIIEpAIBAAKCAQEAt8Vz7BmQyfEbfSaNDt41Evj041PXn8db8PJQmmCPqHT8P4M4\r\n"
            + "U/8Rx5enm8R4n2L9xYydCvNsl84WA0dUALloJah9UPbA5h/8UWRFuj68PO+ii9iU\r\n"
            + "l7ocAizvs/Wj8UwsFm1QDzfW+lroNc/WImmv29+0aZ/qA7qTtpE1QJJxOUfUfA0H\r\n"
            + "497DzrTRL3Kw7z/hU8vJc8b4ben45dG4a7QR0LtwRLT0BDHiiqPE4JN+XJwEt+8/\r\n"
            + "9iZ0z++fQi8O5bcjt1di0OxcV+JFqSxBWMPpvqSVO2cBKSG5dNvmeNXaDd3skyB2\r\n"
            + "dpgUKRiB9KIUObls5yJx6deOPoAwKA488UeIwwIDAQABAoIBAHI5ca87yMosahil\r\n"
            + "AKJQLY8IXTZ7Y5f1k0k1ZDJAeKqNZEiZ6EYVUdjMg6qHOS5gmjnTB73JxiKpPvd0\r\n"
            + "u1rj4z8c9h9/8DBZizyP5eM4oUoC7PadfOa+EDCt+6PQt8EAxpp+AXgkDiR4osLn\r\n"
            + "10J2jI9PAdVxR0RgEPY2VXI4U9X0qQ4UzqnVrcb8LHcgHUZd0cIAI7Qbqoj8JJSt\r\n"
            + "3k+U3KorxKJp0YJKN9yZ26xn3ZjH1VmM/fFnFHnKn9MfjfubqTa//sYlGPnWiscY\r\n"
            + "IUWBu0jg0voDArnJUA778M2RRT/a9O/Kb8hbOKU2ZnbBzNz2c9PJxGahY6eY7zDS\r\n"
            + "IVmRfLkCgYEA2+DbW8xGmqAccpAtev/NpBXmAjOl5sOAOijnrvcrL0O6EG2Hp0lr\r\n"
            + "4QcDC5HUeomT9I+tw8X5xss1KJELbLWxH/3+zVsQHWvh95Y+rhc5CtIHGGGkohko\r\n"
            + "GA47zeeAYRNHN64ey8Fwu1/gGJ17rcLkVOhnZOLyNnDuQWIZMZCuP18CgYEA1fYW\r\n"
            + "gxtU3l+Id4nUiHEaBC3xGtoY/uIopxjA3fnfUH+Q+7Oqo4MJqgQSmQDleegM7svt\r\n"
            + "gH8zxpd9fMIS1zPqdUHx8RTg3tT6TKSytktlhfAaN68oFOmgcQbst9u5KkOx+5We\r\n"
            + "OsGnUYskPX4JdmDKZHuBkK9ncYAg41fgIOQihR0CgYEAtoNvkkN+V9uPwYjsN60e\r\n"
            + "hZWn1ywICgU96wkJR7LjpdgSGpDQiYvogGHRpUjnls+cgOTC2D79loYXGhVL5nDr\r\n"
            + "FiM/3jY4qjNi3qFYZingr5qnSRXEEsnO/ijSe5gueGYIN6/Q4FExwldTLRuMDjA4\r\n"
            + "WEKRkKzRYV9cZemIAByYhwECgYEAwO+04yrNP+0TqP9/yD19AVHQPa93pLeZgvxe\r\n"
            + "Roxg5Dz0JxSfhezO1SHxB0l4pJIsv1Ti6OFLdgP7I3n59VcaT/xUSFnUYPvabSyC\r\n"
            + "/sFEXh/h9F6dHSaDCAj/gte+xC7hGwWS9lbUI0L+PfkeXUHwn+WDM04IUKACiB8g\r\n"
            + "u2gc/L0CgYBOqm+a6EATlQR6wTOO/CviAZAQ7Aab/25a1F8VaXF0tMBk9MI+kZsl\r\n"
            + "eX97Kx0qDoCdP06bks4xrMBM9xzM2PF6y80jvaMK2ClIB21UWZNVFqFFv7DCcuiJ\r\n"
            + "bKQaZvzFZs4QCrNa6w76KZTFSSqsso5Vd1JR1Z1+7T4HH/HETmeP7Q==\r\n"
            + "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
            + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt8Vz7BmQyfEbfSaNDt41\r\n"
            + "Evj041PXn8db8PJQmmCPqHT8P4M4U/8Rx5enm8R4n2L9xYydCvNsl84WA0dUALlo\r\n"
            + "Jah9UPbA5h/8UWRFuj68PO+ii9iUl7ocAizvs/Wj8UwsFm1QDzfW+lroNc/WImmv\r\n"
            + "29+0aZ/qA7qTtpE1QJJxOUfUfA0H497DzrTRL3Kw7z/hU8vJc8b4ben45dG4a7QR\r\n"
            + "0LtwRLT0BDHiiqPE4JN+XJwEt+8/9iZ0z++fQi8O5bcjt1di0OxcV+JFqSxBWMPp\r\n"
            + "vqSVO2cBKSG5dNvmeNXaDd3skyB2dpgUKRiB9KIUObls5yJx6deOPoAwKA488UeI\r\n"
            + "wwIDAQAB\r\n"
            + "-----END PUBLIC KEY-----";
}
