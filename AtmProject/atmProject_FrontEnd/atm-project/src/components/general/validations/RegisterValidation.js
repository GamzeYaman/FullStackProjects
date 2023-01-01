import React from "react";
import * as yup from 'yup';

let RegisterValidation = yup.object().shape({
    customerName: yup.string().trim().matches('^[a-zA-ZğüşöçıIĞÜŞÖÇ]+$', 'İsim sadece harflerden oluşmalı!').required('Zorunlu Alan!'),
    customerSurname: yup.string().trim().matches('^[a-zA-ZğüşöçıIĞÜŞÖÇ]+$', 'Soyisim sadece harflerden oluşmalı!').required('Zorunlu Alan!'),
    customerBirthdate: yup.date().required('Zorunlu Alan!'),
    mobilePhoneNumber: yup.string().trim().matches('[0-9]+', 'Telefon numarası sadece rakamlardan oluşmalı!').min(11, 'Telefon numarası 11 haneli olmalı!').max(11, 'Telefon numarası 11 haneli olmalı!').required('Zorunlu Alan!'),
    password: yup.string().min(5, 'Şifre en az 5 karakterli olmalı!').required('Zorunlu Alan!'),
    customerTc: yup.number().test('len', 'Tc kimlik numarası 11 haneli olmalı!', (val) => { if(val) return val.toString().length === 11; }).required('Zorunlu Alan!')
});

export default RegisterValidation;
