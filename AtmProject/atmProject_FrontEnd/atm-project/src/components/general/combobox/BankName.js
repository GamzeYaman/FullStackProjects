import React from 'react';
import Combobox from "./Combobox";

class BankName extends React.Component {
    render() {
         return (
        <Combobox 
        fieldName = {this.props.name}
        notNull = {true}
        items = {[{id: "ZiraatBankası", name: "Ziraat Bankası"}, {id: "İşBankası", name: "İş Bankası"}]}>
        </Combobox>
    )
    }
   
}

export default BankName;
