package com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection;

import javax.inject.Scope;

/**
 * Marks Dependencies that are limited to a single activity lifecycle.
 *
 * @author Renee Vandervelde
 */
@Scope
public @interface ActivityScope {}
