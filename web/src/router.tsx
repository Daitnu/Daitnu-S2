import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { HomePage, LoginPage, RegisterPage } from './pages';

const AppRouter: React.FC = () => (
  <>
    <Router>
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/login" exact component={LoginPage} />
        <Route path="/register" exact component={RegisterPage} />
      </Switch>
    </Router>
  </>
);

export default AppRouter;
