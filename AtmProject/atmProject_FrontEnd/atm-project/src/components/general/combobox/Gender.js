import React from "react";
import Combobox from "./Combobox";

class Gender extends React.Component {
    render(){
        return(
            <Combobox
            fieldName = {this.props.name}
            notNull= {true}
            items = {[{id: 'Kadın', name: 'Kadın'}, {id: 'Erkek', name:'Erkek'}]}>
            </Combobox>
        )
    }
}

export default Gender;