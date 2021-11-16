package com.example.javafxco1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class jsonObject {

    private @Getter @Setter String Id;
    private @Getter @Setter String Visa;
    private @Getter @Setter String Nom;
    private @Getter @Setter String Prenom;
    private @Getter @Setter String Job;
    private @Getter @Setter String Password;
    private @Getter @Setter String phoneNumber;
}
