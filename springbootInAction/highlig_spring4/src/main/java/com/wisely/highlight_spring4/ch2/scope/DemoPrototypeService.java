package com.wisely.highlight_spring4.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Create by haifei on 22/8/2018.
 */
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
