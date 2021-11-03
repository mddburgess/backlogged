import {Route, Switch} from "react-router-dom";
import {BacklogRoute} from "routes/BacklogRoute";

const Routes = () => (
    <Switch>
        <Route exact path="/backlog" component={BacklogRoute}/>
    </Switch>
);

export default Routes;
