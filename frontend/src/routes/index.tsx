import {Route, Switch} from "react-router-dom";
import BacklogRoute from "routes/BacklogRoute";
import BacklogDetailRoute from "routes/BacklogDetailRoute";

const Routes = () => (
    <Switch>
        <Route exact path="/backlog" component={BacklogRoute}/>
        <Route exact path="/backlog/:id" component={BacklogDetailRoute}/>
    </Switch>
);

export default Routes;
