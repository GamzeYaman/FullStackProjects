import React, { useState } from 'react';
import { Button, Form } from 'react-bootstrap';
import {useFormik } from 'formik';
import '../../css/AuthenticationCss.css';
import RegisterValidation from '../general/validations/RegisterValidation'
import AuthenticationService from '../../api/AuthenticationService';
import BankName from '../general/combobox/BankName';
import Gender from '../general/combobox/Gender';
import serialize from 'form-serialize';
import ToastMessage from '../general/ToastMessage';
import { useNavigate } from "react-router-dom";

function Register(props) {

    let navigate = useNavigate();

    const [state, setState] = useState({
    })

    const formik = useFormik({
        initialValues: {
            customerTc: '',
            customerName: '',
            customerSurname: '',
            customerBirthdate: '',
            mobilePhoneNumber: '',
            password: ''
        },
        onSubmit: values => {
            console.log(values)
        },
        validationSchema: RegisterValidation
    })

    const handleFormSubmit = (e) => {
        e.preventDefault();
        const newCustomer = serialize(e.target, { hash: true })
        saveCustomer(newCustomer);
        navigate("/")
    }

    const saveCustomer = (newCustomer) => {
        AuthenticationService.register(newCustomer).then(response => handleResponse(response)).catch(error => handleError(error));
    }

    const handleResponse = (response) => {
        console.log(response);
    }

    const handleError = (error) => {
        console.log(error.data);
    }

    return (
        <Form className='form-css' onSubmit={handleFormSubmit}>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">TC Kimlik Numarası </Form.Label>
                <Form.Control className='register-input'
                    name="customerTc"
                    type='integer'
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                {formik.errors.customerTc && formik.touched.customerTc && <div className='errors'>{formik.errors.customerTc}</div>}
            </Form.Group>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">İsim </Form.Label>
                <Form.Control className='register-input'
                    name="customerName"
                    type="text"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                {formik.errors.customerName && formik.touched.customerName && <div className='errors'>{formik.errors.customerName}</div>}
            </Form.Group>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">Soyisim </Form.Label>
                <Form.Control className='register-input'
                    name="customerSurname"
                    type="text"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                {formik.errors.customerSurname && formik.touched.customerSurname && <div className='errors'>{formik.errors.customerSurname}</div>}
            </Form.Group>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">Doğum Tarihi</Form.Label>
                <Form.Control className='register-input'
                    name="customerBirthdate"
                    type="date"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                {formik.errors.customerBirthdate && formik.touched.customerBirthdate && <div className='errors'>{formik.errors.customerBirthdate}</div>}
            </Form.Group>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">Cinsiyet</Form.Label>
                <Gender
                    name="gender"
                    notNull={true}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                ></Gender>
            </Form.Group>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">Telefon Numarası</Form.Label>
                <Form.Control className='register-input'
                    name="mobilePhoneNumber"
                    type="text"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                {formik.errors.mobilePhoneNumber && formik.touched.mobilePhoneNumber && <div className='errors'>{formik.errors.mobilePhoneNumber}</div>}
            </Form.Group>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">Şifre</Form.Label>
                <Form.Control className='register-input'
                    name="password"
                    type="password"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                {formik.errors.password && formik.touched.password && <div className='errors'>{formik.errors.password}</div>}
            </Form.Group>

            <Form.Group className='form-group'>
                <Form.Label className="register-label">Banka</Form.Label>
                <BankName
                    name="bank"
                    notNull={true}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}>
                </BankName>
            </Form.Group>

            <input type="submit" className='button-css' value="Kayıt Ol" />
        </Form>     
    );

}

export default Register;